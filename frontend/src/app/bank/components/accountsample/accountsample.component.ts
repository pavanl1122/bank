import { Component } from '@angular/core';
import { AccountTS } from '../../types/tstypes/Accountts';
import { Account } from '../../types/Account';

@Component({
  selector: 'app-accountsample',
  standalone: true,
  imports: [],
  templateUrl: './accountsample.component.html',
  styleUrls: ['./accountsample.component.css']
})
export class AccountsampleComponent {

  account : AccountTS ;
  formattedBalance :string;
  constructor(){
    this.account = new AccountTS("1",1000,"1")
    this.formattedBalance=this.account.balance.toFixed(2);
  }
}
