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
    },
    name:{
        type: DataTypes.STRING,
        allowNull: true
    },
    nic:{
        type: DataTypes.STRING,
        allowNull: true
    }
});

export default User;