import React from "react";
import validate from "./validators/medication-validators";
import * as API_MEDICATIONS from "./api/medication-api";
import TextInput from "./fields/TextInput";
import Button from "react-bootstrap/Button";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";

class MedicationFormDelete extends React.Component{


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
                    placeholder: 'Medication name',
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

    deleteMedication(medication){
        console.log("Step 1");
        return API_MEDICATIONS.deleteMedication(medication, (result, status, error) => {
            console.log(result);

            if(result !== null && (status === 200 || status ===201)){
                console.log("Successfully deleted medication); //: " + result);
                this.props.refresh();
            } else {
                this.setState({errorStatus: status});
                this.error = error;
            }
        });
    }



    handleSubmit(){
        this.deleteMedication(this.state.formControls.name.value);
    }

    render() {
        return (

            <form onSubmit={this.handleSubmit}>

                <h1>Delete medication</h1>

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

export default MedicationFormDelete;