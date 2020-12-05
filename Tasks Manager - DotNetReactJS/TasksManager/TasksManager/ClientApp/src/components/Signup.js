import React, { Component } from "react";
import * as axios from 'axios';
import { Redirect } from "react-router-dom";

export class SignUp extends Component {

    constructor(props) {
        super(props)
        this.state = { name: '', email: '', password: '', badSignup: false };
        this.handlePageChange = this.handlePageChange.bind(this)
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handlePageChange() {

        this.props.handleSignup()
    }


    handleChange(event) {
        const value = event.target.value;
        const name = event.target.name;

        this.setState({
            [name]: value
        });
    }

    async performSignup() {
        const response = await axios.create({ baseURL: 'https://localhost:44352/api/users/signup' }).post('', { "Name": this.state.name, "Email": this.state.email, "Password": this.state.password })
        return response
    }

    async handleSubmit(event) {
        event.preventDefault();

        let response = await this.performSignup();
        if (response.data === 1) {
            //signup succesfully
            this.setState({ badSignup: false })
            this.handlePageChange();

        }
        else {
            //some error in login
            this.setState({ badSignup: true })
            
        }

        

    }


    render() {

        if (this.state.badSignup) {
            return (
                <div style={{ marginLeft: '30%', marginRight: '30%', marginTop: '5%' }}>
                    <form onSubmit={this.handleSubmit}>
                        <h3>Register</h3>

                        <div className="form-group">
                            <label>Name</label>
                            <input type="text" className="form-control" placeholder="Name" name='name' value={this.state.name} onChange={this.handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Email</label>
                            <input type="email" className="form-control" placeholder="Enter email" name='email' value={this.state.email} onChange={this.handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" placeholder="Enter password" name='password' value={this.state.password} onChange={this.handleChange} />
                        </div>

                        <button type="submit" className="btn btn-dark btn-lg btn-block">Register</button>
                        <h5 className="forgot-password text-right">
                            Already registered? <a href="/" onClick={this.handlePageChange}>Log in here!</a>
                        </h5>
                        <h5 className="alert alert-warning">
                            Email already registered!
                    </h5>
                    </form>
                </div>
            );
        }

        return (
            <div style={{ marginLeft: '30%', marginRight: '30%', marginTop: '5%'}}>
                <form onSubmit={this.handleSubmit}>
                <h3>Register</h3>

                <div className="form-group">
                    <label>Name</label>
                        <input type="text" className="form-control" placeholder="Name" name='name' value={this.state.name} onChange={this.handleChange} />
                </div>

                <div className="form-group">
                    <label>Email</label>
                        <input type="email" className="form-control" placeholder="Enter email" name='email' value={this.state.email} onChange={this.handleChange}/>
                </div>

                <div className="form-group">
                    <label>Password</label>
                        <input type="password" className="form-control" placeholder="Enter password" name='password' value={this.state.password} onChange={this.handleChange}/>
                </div>

                <button type="submit" className="btn btn-dark btn-lg btn-block">Register</button>
                    <h5 className="forgot-password text-right">
                        Already registered? <a href="/" onClick={this.handlePageChange}>Log in here!</a>
                    </h5>
                </form>
                </div>
        );
    }
}