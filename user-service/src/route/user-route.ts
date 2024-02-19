import express from "express";
import * as UserController from "../controller/user-controller";
import * as middleware from "../middleware/index";

const router = express.Router();

router.get('/all',middleware.verifyToken, UserController.getAllUsers)
router.post('/add', UserController.createNewUser)
router.post('/auth', UserController.authUser)
router.delete('/delete',middleware.verifyToken, UserController.deleteUser);
router.put('/update',middleware.verifyToken, UserController.updateUser);
router.get('/get',middleware.verifyToken, UserController.searchUser);
router.get('/count/:role', UserController.getCountByRole);

export default router;