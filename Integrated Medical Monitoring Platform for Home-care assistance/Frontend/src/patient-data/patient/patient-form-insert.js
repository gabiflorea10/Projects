import React from 'react';
import validate from "./validators/patient-validators";
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import Button from "react-bootstrap/Button";
import * as API_PATIENTS from "./api/patient-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";


class PatientFormInsert extends React.Component{


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

                gender: {
                    value: '',
                    placeholder: 'Gender',
                    valid: false,
                    touched: false,
                },

                medical_record: {
                    value: '',
                    placeholder: 'Medical record',
                    valid: false,
                    touched: false,
                },

                caregiver_username: {
                    value: '',
                    placeholder: 'Caregiver username',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
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

    insertPatient(patient){
        console.log("The patient is:", patient);
        return API_PATIENTS.postPatient(patient, (result, status, error) => {
            console.log(result);

            if(result !== null && (status === 200 || status ===201)){
                console.log("Successfully inserted patient); //: " + result);
                this.props.refresh();
            } else {
                this.setState({errorStatus: status});
                this.error = error;
            }
        });
    }



    handleSubmit(){

        console.log("New patient data:");
        console.log("Name: " + this.state.formControls.name.value);
        console.log("Username: " + this.state.formControls.username.value);
        console.log("Password: " + this.state.formControls.password.value);
        console.log("Birthdate: " + this.state.formControls.birthdate.value);
        console.log("Gender: " + this.state.formControls.gender.value);
        console.log("Medical record: " + this.state.formControls.medical_record.value);
        console.log("Caregiver username: " + this.state.formControls.caregiver_username.value);

        let patient = {
            name: this.state.formControls.name.value,
            username: this.state.formControls.username.value,
            password: this.state.formControls.password.value,
            birthdate: this.state.formControls.birthdate.value,
            gender: this.state.formControls.gender.value,
            medical_record: this.state.formControls.medical_record.value,
            caregiver_username: this.state.formControls.caregiver_username.value
        };

        this.insertPatient(patient);
    }

    render() {
        return (

            <form onSubmit={this.handleSubmit}>

                <h1>Insert new patient</h1>

                <p> Name: </p>

                <TextInput name="name"
                           placeholder={this.state.formControls.name.placeholder}
                           value={this.state.formControls.name.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.name.touched}
                           valid={this.state.formControls.name.valid}
                />
                {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                <div className={"error-message row"}> * Medication name must have at least 3 characters </div>}


                <p> Username: </p>

                <TextInput name="username"
                           placeholder={this.state.formControls.username.placeholder}
                           value={this.state.formControls.username.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.username.touched}
                           valid={this.state.formControls.username.valid}
                />
                {this.state.formControls.username.touched && !this.state.formControls.username.valid &&
                <div className={"error-message row"}> * Username must have at least 3 characters </div>}

                <p> Password: </p>

                <TextInput name="password"
                           placeholder={this.state.formControls.password.placeholder}
                           value={this.state.formControls.password.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.password.touched}
                           valid={this.state.formControls.password.valid}
                />
                {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                <div className={"error-message row"}> * Password must have at least 3 characters </div>}

                <p> Birthdate: </p>

                <TextInput name="birthdate"
                           placeholder={this.state.formControls.birthdate.placeholder}
                           value={this.state.formControls.birthdate.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.birthdate.touched}
                           valid={this.state.formControls.birthdate.valid}
                />

                <p> Gender: </p>

                <TextInput name="gender"
                           placeholder={this.state.formControls.gender.placeholder}
                           value={this.state.formControls.gender.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.gender.touched}
                           valid={this.state.formControls.gender.valid}
                />


                <p> Medical record: </p>
                <TextInput name="medical_record"
                           placeholder={this.state.formControls.medical_record.placeholder}
                           value={this.state.formControls.medical_record.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.medical_record.touched}
                           valid={this.state.formControls.medical_record.valid}
                />

                <p> Caregiver username: </p>
                <TextInput name="caregiver_username"
                           placeholder={this.state.formControls.caregiver_username.placeholder}
                           value={this.state.formControls.caregiver_username.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.caregiver_username.touched}
                           valid={this.state.formControls.caregiver_username.valid}
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

export default PatientFormInsert;
