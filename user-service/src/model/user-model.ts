import { Model, DataTypes } from 'sequelize';
import sequelize from '../middleware/sequelize';

const User = sequelize.define('user', {
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        unique: true
    },
    email: {
        type: DataTypes.STRING,
        allowNull: true,
    },
    password: {
        type: DataTypes.STRING,
        allowNull: false
    },
    role: {
        type: DataTypes.ENUM('student', 'teacher'),
        allowNull: false,
        defaultValue: 'student'
    },
    name:{
        type: DataTypes.STRING,
        allowNull: true
    },
    nic:{
        type: DataTypes.STRING,
        allowNull: true,
        primaryKey: true
    }
});

export default User;