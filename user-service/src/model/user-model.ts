//use mysql database to store user data. user data is email, password, and role

import { Model, DataTypes } from 'sequelize';
import sequelize from '../middleware/sequelize';

const User = sequelize.define('user', {
    email: {
        type: DataTypes.STRING,
        primaryKey: true,
    },
    password: {
        type: DataTypes.STRING,
        allowNull: false
    },
    role: {
        type: DataTypes.ENUM('student', 'teacher'),
        allowNull: false,
        defaultValue: 'student'
    }
});

export default User;