import React, { Component } from "react";
import * as axios from 'axios';

export class Login extends Component {

    constructor(props) {
        super(props);
        this.state = { email: '', password: '', badLogin: false };

        this.handleChange = this.handleChange.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handlePageChange() {
        
        this.props.gotoSignup()
      
    }

    handleChange(event) {

        const value = event.target.value;
        const name = event.target.name;


        this.setState({
            [name]: value
        });

    }

    async performLogin() {
        const response = await axios.create({ baseURL: 'https://localhost:44352/api/users' }).post('', { "Email": this.state.email, "Password": this.state.password })
        return response
    }

    async handleSubmit(event) {
        event.preventDefault();

        let response = await this.performLogin();
        if (response.data === 1) {
            //login successful
            
            sessionStorage.setItem('currentUser', this.state.email)
            this.setState({ badLogin: false })
            this.props.handleLogin();
        }
        else {
            //some error in login
            this.setState({ badLogin: true })
        }

       
    }

        render() {

    if (this.state.badLogin) {
        return (
            <div style={{ marginLeft: '30%', marginRight: '30%', marginTop: '5%' }}>
                <h1 style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>Tasks manager</h1>
                <br />
                <form onSubmit={this.handleSubmit}>

                    <h3>Log in</h3>

                    <div className="form-group">
                        <label>Email</label>
                        <input type="email" className="form-control" placeholder="Enter email" name='email' value={this.state.email} onChange={this.handleChange} />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="Enter password" name='password' value={this.state.password} onChange={this.handleChange} />
                    </div>

                    <button type="submit" className="btn btn-dark btn-lg btn-block">Sign in</button>
                    <h5 className="forgot-password text-right">
                        Do you not have an account? <a href="/signup" onClick={ this.handlePageChange}>Sign up here!</a>
                    </h5>
                    <h5 className="alert alert-warning">
                        Wrong credentials!
                    </h5>
                </form>
            </div>
        );
    }

        return (
            <div style={{ marginLeft: '30%', marginRight: '30%', marginTop: '5%'}}>
            <h1 style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>Tasks manager</h1>
            <br/>
                <form onSubmit={this.handleSubmit}>
               
                <h3>Log in</h3>

                <div className="form-group">
                    <label>Email</label>
                        <input type="email" className="form-control" placeholder="Enter email" name='email' value={this.state.email} onChange={this.handleChange} />
                </div>

                <div className="form-group">
                    <label>Password</label>
                        <input type="password" className="form-control" placeholder="Enter password" name='password' value={this.state.password} onChange={this.handleChange} />
                </div>

                <button type="submit" className="btn btn-dark btn-lg btn-block">Sign in</button>
                <h5 className="forgot-password text-right">
                        Do you not have an account? <a href="/signup" onClick={this.handlePageChange}>Sign up here!</a>
                </h5>
                </form>
            </div>
        );
    }
}