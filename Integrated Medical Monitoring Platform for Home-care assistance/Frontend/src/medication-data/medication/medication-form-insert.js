import React from 'react';
import validate from "./validators/medication-validators";
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import Button from "react-bootstrap/Button";
import * as API_MEDICATIONS from "./api/medication-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";


class MedicationFormInsert extends React.Component{

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

                side_effects: {
                    value: '',
                    placeholder: 'Side effects',
                    valid: false,
                    touched: false,
                },

                dosage: {
                    value: '',
                    placeholder: 0,
                    valid: false,
                    touched: false,
                    validationRules: {
                        isInteger: true
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

    insertMedication(medication){
        return API_MEDICATIONS.postMedication(medication, (result, status, error) => {
            console.log(result);

            if(result !== null && (status === 200 || status ===201)){
                console.log("Successfully inserted medications); //: " + result);
                this.props.refresh();
            } else {
                this.setState({errorStatus: status});
                this.error = error;
            }
        });
    }



    handleSubmit(){

        console.log("New medication data:");
        console.log("Name: " + this.state.formControls.name.value);
        console.log("Side effects: " + this.state.formControls.side_effects.value);
        console.log("Dosage: " + this.state.formControls.dosage.value);

        let medication = {
            name: this.state.formControls.name.value,
            side_effects: this.state.formControls.side_effects.value,
            dosage: parseInt(this.state.formControls.dosage.value, 10),
        };

        this.insertMedication(medication);
    }

    render() {
      return (

    <form onSubmit={this.handleSubmit}>

        <h1>Insert new medication</h1>

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

        <p> Side effects: </p>
        <TextInput name="side_effects"
                   placeholder={this.state.formControls.side_effects.placeholder}
                   value={this.state.formControls.side_effects.value}
                   onChange={this.handleChange}
                   touched={this.state.formControls.side_effects.touched}
                   valid={this.state.formControls.side_effects.valid}
        />

        <p> Dosage: </p>
        <TextInput name="dosage"
                   placeholder={this.state.formControls.dosage.placeholder}
                   value={this.state.formControls.dosage.value}
                   onChange={this.handleChange}
                   touched={this.state.formControls.dosage.touched}
                   valid={this.state.formControls.dosage.valid}
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


export default MedicationFormInsert;
