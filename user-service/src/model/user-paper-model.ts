import { Model, DataTypes } from 'sequelize';
import sequelize from '../middleware/sequelize';

const UserPaper = sequelize.define('user_paper', {
    email: {
        type: DataTypes.STRING,
        primaryKey: true,
    },
    paper_id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
    },
    score: {
        type: DataTypes.INTEGER,
        allowNull: false
    }
});

export default UserPaper;