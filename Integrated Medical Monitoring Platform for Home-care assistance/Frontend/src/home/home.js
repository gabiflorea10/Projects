import React from 'react';
import {Redirect} from 'react-router-dom';
import BackgroundImg from '../commons/images/future-medicine.jpg';

import {Button, Container, Jumbotron} from 'reactstrap';
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Label from "reactstrap/es/Label";
import Input from "reactstrap/es/Input";
import * as API_HOME from "../home/api/home-api";

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1920px",
    backgroundImage: `url(${BackgroundImg})`
};
const textStyle = {color: 'white'
};

const textLogin = {
    color: 'white',
    fontSize: 20
};

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            performRedirect: false
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    };

    handleSubmit = (event) =>{
        event.preventDefault();
        this.setState({ performRedirect: false })
        sessionStorage.clear();

        const username = this.state.username;
        const password = this.state.password;

        console.log(username);
        API_HOME.getLogin(username, password, (result, status, error) => {
            console.log(result);

            if(result !== null && (status === 200 || status ===201)){
                console.log("Successfully logged in!");
                sessionStorage.setItem('name', result.name);
                sessionStorage.setItem('username', result.username);
                sessionStorage.setItem('role', result.role);

                this.setState({ performRedirect: true })

                // const role = sessionStorage.getItem('role');
                //
                // if(!role.localeCompare("caregiver")){
                //     console.log("Near redirect");
                //
                // }



                //this.props.refresh();
            } else {
                //this.setState({errorStatus: status});
                //this.error = error;
            }
        });


    };

    handleChange = (event) => {
        const target = event.target;
        const field =  target.name;
        const value = target.value;

        this.setState({
            [field]:  value
        });
    };

    render() {

        if(this.state.performRedirect){
            const role = sessionStorage.getItem('role');
            if(!role.localeCompare("doctor")){
                return <Redirect  to="/doctorHome/"/>;
            }
            else if (!role.localeCompare("caregiver")){
                return <Redirect  to="/caregiverHome/"/>;
            }
            else if(!role.localeCompare("patient")){
                return <Redirect  to="/patientHome/"/>;
            }
        }
        sessionStorage.setItem('table', 'other');
        return (

            <div>
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>
                        <h3 style={textStyle}>Integrated Medical Monitoring Platform for Home-care assistance</h3>
                        <p className="lead" style={textStyle}> <b>Enabling real time monitoring of patients, remote-assisted care services and
                            smart intake mechanism for prescribed medication.</b> </p>
                        <hr className="my-2"/>
                        <p>

                        </p>

                        <div className="container">
                            <Form onSubmit={this.handleSubmit}>
                                <FormGroup>
                                    <Label for="username" style={textLogin}>Username</Label>
                                    <Input
                                        type="text"
                                        name="username"
                                        value={this.state.username}
                                        onChange={this.handleChange}
                                        id="username"
                                        placeholder="Enter your username"
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="password" style={textLogin}>Password</Label>
                                    <Input
                                        type="password"
                                        name="password"
                                        value={this.state.password}
                                        onChange={this.handleChange}
                                        id="password"
                                        placeholder="Enter your password."
                                    />
                                </FormGroup>
                                <Button variant="success"
                                        type={"submit"}>login</Button>
                            </Form>
                        </div>

                    </Container>
                </Jumbotron>

            </div>
        )
    };
}

export default Home
