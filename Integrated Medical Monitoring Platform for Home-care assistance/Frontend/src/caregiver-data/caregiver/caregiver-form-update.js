import React from 'react';
import validate from "./validators/caregiver-validators";
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import Button from "react-bootstrap/Button";
import * as API_CAREGIVERS from "./api/caregiver-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";


class CaregiverFormUpdate extends React.Component{


    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls : {

                name: {
                    value: '',
                    placeholder: 'Name',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },

                username: {
                    value: '',
                    placeholder: 'Username',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },

                password: {
                    value: '',
                    placeholder: 'Password',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },

                birthdate: {
                    value: '',
                    placeholder: 'Birthdate',
                    valid: false,
                    touched: false,
                },
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    componentDidMount() {

    }


    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = {
            ...this.state.formControls
        };

        const updatedFormElement = {
            ...updatedControls[name]
        };

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);

        console.log("Element: " +  name + " validated: " + updatedFormElement.valid);

        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });
    };

    updateCaregiver(caregiver){
        console.log(caregiver);
        return API_CAREGIVERS.putCaregiver(caregiver, (result, status, error) => {
            console.log(result);

            if(result !== null && (status === 200 || status ===201)){
                console.log("Successfully updated caregiver); //: " + result);
                this.props.refresh();
            } else {
                this.setState({errorStatus: status});
                this.error = error;
            }
        });
    }



    handleSubmit(){

        let caregiver = {
            name: this.state.formControls.name.value,
            username: this.state.formControls.username.value,
            password: this.state.formControls.password.value,
            birthdate: this.state.formControls.birthdate.value,
        };

        this.updateCaregiver(caregiver);
    }

    render() {
        return (

            <form onSubmit={this.handleSubmit}>

                <h1>Update caregiver</h1>

                <p>Update caregiver with username: </p>

                <TextInput name="username"
                           placeholder={this.state.formControls.username.placeholder}
                           value={this.state.formControls.username.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.username.touched}
                           valid={this.state.formControls.username.valid}
                />
                {this.state.formControls.username.touched && !this.state.formControls.username.valid &&
                <div className={"error-message row"}> * Username must have at least 3 characters </div>}

                <p>Change name to: </p>

                <TextInput name="name"
                           placeholder={this.state.formControls.name.placeholder}
                           value={this.state.formControls.name.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.name.touched}
                           valid={this.state.formControls.name.valid}
                />
                {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                <div className={"error-message row"}> * Name must have at least 3 characters </div>}

                <p>Change password to: </p>

                <TextInput name="password"
                           placeholder={this.state.formControls.password.placeholder}
                           value={this.state.formControls.password.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.password.touched}
                           valid={this.state.formControls.password.valid}
                />
                {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                <div className={"error-message row"}> * Password must have at least 3 characters </div>}

                <p>Change birthdate to: </p>

                <TextInput name="birthdate"
                           placeholder={this.state.formControls.birthdate.placeholder}
                           value={this.state.formControls.birthdate.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.birthdate.touched}
                           valid={this.state.formControls.birthdate.valid}
                />

                <p></p>
                <Button variant="success"
                        type={"submit"}
                        disabled={!this.state.formIsValid}>
                    Submit
                </Button>

                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </form>

        );
    }
}

export default CaregiverFormUpdate;
