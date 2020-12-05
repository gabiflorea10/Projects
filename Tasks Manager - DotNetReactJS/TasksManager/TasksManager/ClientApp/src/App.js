import React, { Component } from 'react';
import { Route , Switch, Redirect } from 'react-router';
import { FetchTodos } from './components/FetchTodos';
import { About } from './components/About';
import { Login } from './components/Login';
import { NavMenu } from './components/NavMenu';
import { SignUp } from './components/Signup';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './custom.css'

export default class App extends Component {
    static displayName = App.name;

    constructor(props) {
        super(props)
        let isLoggedInStatus = sessionStorage.getItem("LoginStatus");
        let signupModeQ = sessionStorage.getItem("signupMode");

        this.state = { isLoggedIn: isLoggedInStatus === null ? false : isLoggedInStatus, signupMode: signupModeQ === null ? false: signupModeQ }
        this.handleLogin = this.handleLogin.bind(this)
        this.handleLogout = this.handleLogout.bind(this)
        this.handleSignup = this.handleSignup.bind(this)
        this.gotoSignup = this.gotoSignup.bind(this)
    }

    handleLogin() {
        this.setState({ isLoggedIn: true })
        sessionStorage.setItem("LoginStatus", true)
    }

    handleLogout() {
        this.setState({ isLoggedIn: false })
        sessionStorage.removeItem("currentUser")
        sessionStorage.removeItem("LoginStatus")

    }

    gotoSignup() {
  
        this.setState({ signupMode: true })
        sessionStorage.setItem("signupMode", true)
    }

    handleSignup() {
        this.setState({ signupMode: false })
        sessionStorage.removeItem("signupMode")
    }


    render() {
 

        let signupMode = this.state.signupMode


        if (this.state.isLoggedIn) {
            return (<div>
                <NavMenu login={this.state.isLoggedIn} handleLogout={this.handleLogout} signupModePar={signupMode} />
                <Switch>
                    <Route exact path='/todos'>
                        <FetchTodos />
                    </Route>
                    <Route exact path='/about'>
                        <About />
                    </Route>
                </Switch>
                <Redirect to='/todos' />
            </div>)
        }

        else if (signupMode) {
            return (<div>
                <NavMenu login={this.state.isLoggedIn} signupModePar={signupMode} />

                <Switch>
                    <Route exact path='/'>
                        <Login
                            handleLogin={this.handleLogin} gotoSignup={this.gotoSignup}
                        />
                    </Route>
                    <Route exact path='/signup'>
                        <SignUp handleSignup={this.handleSignup} />
                    </Route>
                    <Route exact path='/about'>
                        <About />
                    </Route>
                </Switch>


            </div>)
        }

        else {
            return (
                <div>
                    <NavMenu login={this.state.isLoggedIn} signupModePar={signupMode} />

                    <Switch>
                        <Route exact path='/'>
                            <Login
                                handleLogin={this.handleLogin} gotoSignup={this.gotoSignup}
                            />
                        </Route>
                        <Route exact path='/signup'>
                            <SignUp handleSignup={this.handleSignup} />
                        </Route>
                        <Route exact path='/about'>
                            <About />
                        </Route>
                    </Switch>
                    <Redirect to='/' />
                </div>
            );
        }
    }
}
