import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signUpForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.signUpForm = this.formBuilder.group({
      email: ['', Validators.email],
      password: [''],
      name: ['', Validators.pattern('^[A-Za-z]{2,50}$')],
      surname: ['', Validators.pattern('^[A-Za-z]{2,50}$')]
    })
  }

  ngOnInit(): void {

  }

}
