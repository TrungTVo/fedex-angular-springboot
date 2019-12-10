import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User.model';
import { Store, select } from '@ngrx/store';
import * as rootReducers from '../../reducers/index';
import * as UserActions from '../../actions/user.action';
import { NgxSpinnerService } from "ngx-spinner";
import { Router } from '@angular/router';
import * as ConsignmentActions from '../../actions/consignment.action';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  current_user: User;
  users_list: User[] = [];
  success_msg: string;
  error_msg: string;
  addBtn_show: boolean = true;

  constructor(
    private store: Store<rootReducers.AppState>,
    private spinner: NgxSpinnerService,
    private router: Router
  ) { }

  ngOnInit() {
    this.store.dispatch(UserActions.LOAD_USERS());

    this.store.pipe(select('success')).subscribe(info => {
      this.success_msg = info.success_msg;
    })

    this.store.pipe(select('error')).subscribe(info => {
      this.error_msg = info.error_msg;
    })

    this.store.pipe(select('user')).subscribe(res => {
      this.current_user = res.current_user;
      this.users_list = res.allUsers;

      if (res.loading) {
        this.spinner.show();
      } else {
        this.spinner.hide();
      }
    })
  }

  onAdd() {
    this.addBtn_show = false;
  }

  onDelete(user) {
    const payload = {
      email: user.email,
      current_user: this.current_user
    }
    this.store.dispatch(UserActions.DELETE_USER({ payload: payload }));
  }

}
