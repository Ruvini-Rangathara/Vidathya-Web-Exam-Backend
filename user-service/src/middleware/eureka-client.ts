import {Eureka} from "eureka-js-client";
import process from "process";
const appName = process.env.APP_NAME || 'user-service';

console.log('appName:', appName);

let eureka:Eureka = new Eureka({
    instance: {
        app: appName,
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        port: {
            $: 9091,  // Port number
            '@enabled': true, // Whether the port is enabled
        },
        vipAddress: appName,
        dataCenterInfo: {
            name: 'MyOwn',
        },
    },
    eureka: {
        host: 'localhost',
        port: 8080,
    },
});

export default eureka;