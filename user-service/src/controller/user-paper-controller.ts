import express from "express";
import CustomResponse from "../dto/CustomResponse";
import UserPaper from "../model/user-paper-model";

interface UserPaperRequestBody{
    email: string;
    paper_id: number;
    score: number;
}

export const getAllUserPapers = async (req: express.Request, res: express.Response) => {
    try {
        let items = await UserPaper.findAll();
        res.status(200).send(
            new CustomResponse(200, "User Paper are found successfully", items)
        );
    } catch (error) {
        res.status(100).send("Error")
    }
}

export const createNewUserPaper = async (req: express.Request, res: express.Response) => {
    try {
        const req_body: any = req.body;
        const newUserPaper = await UserPaper.create({
            email: req_body.email,
            paper_id: req_body.paper_id,
            score: req_body.score
        });
        return res.status(200).send(
            new CustomResponse(200, "User Paper created successfully", newUserPaper)
        );
    } catch (error) {
        console.error("Error creating user paper:", error);
        return res.status(500).send("Error");
    }
};

export const getUserPaper = async (req: express.Request, res: express.Response) => {
    try {
        const email = req.params.email;
        const paper_id = req.params.paper_id;
        let userPaper = await UserPaper.findOne({
            where: {
                email: email,
                paper_id: paper_id
            }
        });
        res.status(200).send(
            new CustomResponse(200, "User Paper is found successfully", userPaper)
        );
    } catch (error) {
        res.status(100).send("Error")
    }
}

export const updateUserPaper = async (req: express.Request, res: express.Response) => {
    try {
        const req_body: any = req.body;
        const email = req.params.email;
        const paper_id = req.params.paper_id;
        const userPaper = await UserPaper.update({
            score: req_body.score
        }, {
            where: {
                email: email,
                paper_id: paper_id
            }
        });
        res.status(200).send(
            new CustomResponse(200, "User Paper is updated successfully", userPaper)
        );
    } catch (error) {
        res.status(100).send("Error")
    }
}

export const deleteUserPaper = async (req: express.Request, res: express.Response) => {
    try {
        const email = req.params.email;
        const paper_id = req.params.paper_id;
        await UserPaper.destroy({
            where: {
                email: email,
                paper_id: paper_id
            }
        });
        res.status(200).send(
            new CustomResponse(200, "User Paper is deleted successfully")
        );
    } catch (error) {
        res.status(100).send("Error")
    }
}

export const getUserPapersByUser = async (req: express.Request, res: express.Response) => {
    try {
        const email = req.params.email;
        let userPapers = await UserPaper.findAll({
            where: {
                email: email
            }
        });
        res.status(200).send(
            new CustomResponse(200, "User Papers are found successfully", userPapers)
        );
    } catch (error) {
        res.status(100).send("Error")
    }
}