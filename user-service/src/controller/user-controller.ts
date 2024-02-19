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
    nic: string;
    password: string;
}

export const getAllUsers = async (req: Request, res: Response) => {
    try {
        console.log("role : ",req.params.role)
        const role = req.params.role;
        let  users = await User.findAll({
            where: {
                role: role
            }
        });
        res.status(200).send(
            new CustomResponse(200, "Users are found successfully", users)
        );
    } catch (error) {
        res.status(100).send("Error")
    }
}

export const getCountByRole = async (req: Request, res: Response) => {
    try {
        const { role } = req.params;
        console.log("role : ", role);
        const count = await User.count({
            where: {
                role: role
            }
        });
        console.log("count : ", count);
        res.status(200).send(
            new CustomResponse(200, "Users are found successfully", count)
        );
    } catch (error) {
        console.error("Error:", error);
        res.status(500).send("Error");
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
                    nic: req_body.nic,
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
        const {nic, password}: AuthRequestBody = req.body;
        const user: Model<any> | null = await UserModel.findOne({where: {nic}});

        console.log("user : ",req.body)

        if (user) {
            const p = user.get('password');
            console.log("p : ",p)
            console.log("password : ",password);

            const isMatch = await bcrypt.compare(password, p as string);
            // const isMatch = (password === p as string);

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
                console.log("Password not matched");
                // If passwords don't match, send invalid credentials error response
                return res.status(401).send(new CustomResponse(401, "Invalid credentials"));
            }
        } else {
            console.log("User not found");
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
        const nic = decoded.user.nic;

        try {
            await User.destroy({ where: { nic: nic } });
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
    console.log("req : ",req.body)
    const req_body: any = req.body;
    await bcrypt.hash(req_body.password, 8, async function (err, hash) {
        if (err) {
            return res.status(500).send(
                new CustomResponse(500, "Something went wrong.")
            );
        }

        try {
            const token = req.headers.authorization?.split(' ')[1]; // Assuming token is sent in the
            console.log("token : ",token)
            const decoded: any = jwt.decode(token!);
            console.log(decoded.user.nic)
            const nic = decoded.user.nic;

            const req_body: any = req.body;
            const user = {
                id: req_body.id,
                email: req_body.email,
                password: hash,
                role: req_body.role,
                name: req_body.name,
                nic: nic
            };


            try {
                await User.update(user, { where: { nic: req_body.nic } });
                return res.status(200).send(new CustomResponse(200, "User updated successfully"));
            } catch (error) {
                console.error("Error updating user:", error);
                return res.status(500).send(new CustomResponse(500, "Failed to update user"));
            }
        } catch (error) {
            console.error("Error:", error);
            return res.status(500).send(new CustomResponse(500, "Error"));

        }
    });



}

export const searchUser = async (req: Request, res: Response) => {
    try {
        const token = req.headers.authorization?.split(' ')[1]; // Assuming token is sent in the
        const decoded: any = jwt.decode(token!);
        const nic = decoded.user.nic;

        let users  = await User.findAll({ where: { nic: nic } });
        console.log("users : ",JSON.stringify(users))

        return res.status(200).send(new CustomResponse(200, "Users are found successfully", users));
    } catch (error) {
        console.error("Error:", error);
        return res.status(500).send(new CustomResponse(500, "Error"));
    }
}