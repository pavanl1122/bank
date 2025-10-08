export class CustomerTS {
  

    customerId? : string
    name : string
    email : string
    username : string
    password : string
    role? : string

    constructor(name : string, email : string, username : string, password : string, role : string, customerId? : string){
        this.name = name;
        this.email= email;
        this.username =username;
        this.password= password;
        this.role = role;
        if(customerId !== null || customerId!== undefined){
            this.customerId = customerId;
        }
        
    }

    displayInfo() : void {

        console.log(`Customer ID: ${this.customerId}`);
        console.log(`Name: ${this.name}`);
        console.log(`Email: ${this.email}`);
        console.log(`Username: ${this.username}`);
        console.log(`Password: ${this.password}`);
        // console.log(`Role : ${this.role}`);
        

        
    }
   
}