import { Creator } from "./creator";
import { Skill } from "./skill";

export class Course {
    id: number;
    title: String;
    description: String;
    prerequisite: String;
    lastupdated: String;
    feedback: String;
    location: String;
    skills: Array<Skill>;
    creator: Array<Creator>;

    constructor(){
        this.id = 0;
        this.title = "";
        this.description = "";
        this.prerequisite = "";
        this.lastupdated = "";
        this.feedback = "";
        this.location = "";
        this.skills = [];
        this.creator = [];
    }
}
