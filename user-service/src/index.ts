import dotenv from 'dotenv';
dotenv.config();
import express from 'express';
import cors from 'cors';
import sequelize from "./middleware/sequelize";
import process from 'process';
import UserRoutes from './route/user-route';
import bodyParser from 'body-parser'; // Import body-parser
import eurekaClient from '././middleware/eureka-client';


const app = express();

app.use(cors({
    origin: '*'
}));

app.use(bodyParser.json()); // Use body-parser middleware

// Start the server
const port = process.env.PORT;
app.listen(port, async () => {
    console.log(`Server started on port ${port}`);
    try {
        await sequelize.authenticate(); // Check database connection
        console.log('Database connection has been established successfully.');
        await sequelize.sync(); // Sync database models
        console.log('Database synchronized successfully');
    } catch (error) {
        console.error('Unable to connect to the database:', error);
    }
});

// Register with Eureka server
// eurekaClient.start((error: Error) => {
//     console.log("=====================================================================")
//     console.log(JSON.stringify(eurekaClient))
//     if (error) {
//         console.log('Error registering with Eureka:', error);
//     } else {
//         console.log('Registered with Eureka successfully');
//     }
// });


app.use('/api/v1/user', UserRoutes);