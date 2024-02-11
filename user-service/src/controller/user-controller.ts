import express from "express";
import CustomResponse from "../dto/CustomResponse";
import User from "../model/user-model";
import bcrypt from "bcryptjs";
import process from "process";
import jwt, {Secret} from "jsonwebtoken";
import UserModel from "../model/user-model";
import {Request, Response} from "express";
import {Model} from "sequelize";

interface AuthRequestBody {
    email: string;
    password: string;
}

export const getAllUsers = async (req: Request, res: Response) => {
    try {
        let users = await User.findAll();
        res.status(200).send(
            new CustomResponse(200, "Users are found successfully", users)
        );
    } catch (error) {
        res.status(100).send("Error")
    }
}

export const createNewUser = async (req: express.Request, res: express.Response) => {
    try {
        const req_body: any = req.body;
        await bcrypt.hash(req_body.password, 8, async function (err, hash) {
            if (err) {
                return res.status(500).send(
                    new CustomResponse(500, "Something went wrong.")
                );
            }

            try {
                const newUser = await User.create({
                    email: req_body.email,
                    password: hash,
                    role: req_body.role
                });

                return res.status(200).send(
                    new CustomResponse(200, "User created successfully", newUser)
                );
            } catch (error) {
                console.error("Error creating user:", error);
                return res.status(500).send(
                    new CustomResponse(500, "Failed to create user.")
                );
            }
        });

    } catch (error) {
        console.error("Error creating user:", error);
        return res.status(500).send("Error");
    }
};

// Login
export const authUser = async (req: Request, res: Response) => {
    try {
        const {email, password}: AuthRequestBody = req.body;
        const user: Model<any> | null = await UserModel.findOne({where: {email}});

        if (user) {
            const p = user.get('password');
            const isMatch = await bcrypt.compare(password, p as string);

            if (isMatch) {
                console.log("Password matched");
                const expiresIn = '1w';
                const secret = process.env.SECRET as Secret;
                jwt.sign({user}, secret, {expiresIn}, (err: any, token: any) => {
                    if (err) {
                        console.error("Failed to generate token:", err);
                        return res.status(500).send(new CustomResponse(500, "Failed to generate token : ", err));
                    }

                    // If token is generated successfully, send success response with token and user data
                    const responseData = {
                        user,
                        accessToken: token
                    };

                    return res.status(200).send(new CustomResponse(200, "Access", responseData));
                });
            } else {
                // If passwords don't match, send invalid credentials error response
                return res.status(401).send(new CustomResponse(401, "Invalid credentials"));
            }
        } else {
            // If user is not found, send user not found error response
            return res.status(404).send(new CustomResponse(404, "User not found"));
        }
    } catch (error) {
        // If any error occurs, log the error and send a generic error response
        console.error("Error:", error);
        return res.status(500).send("Error");
    }
};

export const deleteUser = async (req: Request, res: Response) => {
    try {
        const token = req.headers.authorization?.split(' ')[1]; // Assuming token is sent in the format "Bearer <token>"

        const decoded: any = jwt.decode(token!);
        const userId = decoded.user.email;

        try {
            await User.destroy({ where: { email: userId } });
            return res.status(200).send(new CustomResponse(200, "User deleted successfully"));
        } catch (error) {
            console.error("Error deleting user:", error);
            return res.status(500).send(new CustomResponse(500, "Failed to delete user"));
        }
    } catch (error) {
        console.error("Error:", error);
        return res.status(500).send(new CustomResponse(500, "Error"));
    }
};

export const updateUser = async (req: Request, res: Response) => {
    try {
        const token = req.headers.authorization?.split(' ')[1]; // Assuming token is sent in the
        const decoded: any = jwt.decode(token!);
        const userId = decoded.user.email;

        const req_body: any = req.body;

        try {
            await User.update(req_body, { where: { email: userId } });
            return res.status(200).send(new CustomResponse(200, "User updated successfully"));
        } catch (error) {
            console.error("Error updating user:", error);
            return res.status(500).send(new CustomResponse(500, "Failed to update user"));
        }
    } catch (error) {
        console.error("Error:", error);
        return res.status(500).send(new CustomResponse(500, "Error"));

    }
}

export const searchUser = async (req: Request, res: Response) => {
    try {
        const token = req.headers.authorization?.split(' ')[1]; // Assuming token is sent in the
        const decoded: any = jwt.decode(token!);
        const userId = decoded.user.email;

        const users = await User.findAll({ where: { email: userId } });

        return res.status(200).send(new CustomResponse(200, "Users are found successfully", users));
    } catch (error) {
        console.error("Error:", error);
        return res.status(500).send(new CustomResponse(500, "Error"));
    }
}