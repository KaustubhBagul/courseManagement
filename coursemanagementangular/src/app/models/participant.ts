import { Course } from "./course";

export class Participant {
    id: number;
    username: String;
    name: String;
    email: String;
    password: String;
    courses: Array<Course>;

    constructor(){
        this.id = 0;
        this.username = "";
        this.name = "";
        this.email = "";
        this.password = "";
        this.courses = [];
    }
}
