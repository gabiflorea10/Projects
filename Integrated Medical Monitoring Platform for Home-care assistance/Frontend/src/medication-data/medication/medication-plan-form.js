import React from 'react';
import validate from "./validators/medication-validators";
import TextInput from "./fields/TextInput";
import './fields/fields.css';
import Button from "react-bootstrap/Button";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import * as API_MEDICATION_PLANS from "../../medication-plan-data/medication-plan/api/medication-plan-api"

class MedicationPlanForm extends React.Component{

    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,

            formControls : {

                patient_name: {
                    value: '',
                    placeholder: 'Patient name',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },

                medication_name: {
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

                intake_interval: {
                    value: '',
                    placeholder: 'Intake interval',
                    valid: false,
                    touched: false,
                },

                start_time: {
                    value: '',
                    placeholder: 'Start time',
                    valid: false,
                    touched: false,
                },

                end_time: {
                    value: '',
                    placeholder: 'End time',
                    valid: false,
                    touched: false,
                },

                doctor_name: {
                    value: '',
                    placeholder: 'Doctor name',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                }
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

    insertMedicationPlan(medicationPlan){
        return API_MEDICATION_PLANS.postMedicationPlan(medicationPlan, (result, status, error) => {
            console.log(result);

            if(result !== null && (status === 200 || status ===201)){
                console.log("Successfully inserted medication plan); //: " + result);
                this.props.refresh();
            } else {
                this.setState({errorStatus: status});
                this.error = error;
            }
        });
    }

    handleSubmit(){

        let medicationPlan = {
            patient_name: this.state.formControls.patient_name.value,
            medication_name: this.state.formControls.medication_name.value,
            side_effects: this.state.formControls.side_effects.value,
            dosage: parseInt(this.state.formControls.dosage.value, 10),
            intake_interval: this.state.formControls.intake_interval.value,
            start_time: this.state.formControls.start_time.value,
            end_time: this.state.formControls.end_time.value,
            doctor_name: this.state.formControls.doctor_name.value
        };

        this.insertMedicationPlan(medicationPlan);
    }

    render() {
        return (

            <form onSubmit={this.handleSubmit}>

                <h1>Insert new medication plan</h1>

                <p>Patient name:</p>
                <TextInput name="patient_name"
                           placeholder={this.state.formControls.patient_name.placeholder}
                           value={this.state.formControls.patient_name.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.patient_name.touched}
                           valid={this.state.formControls.patient_name.valid}
                />
                {this.state.formControls.patient_name.touched && !this.state.formControls.patient_name.valid &&
                <div className={"error-message row"}> * Name must have at least 3 characters </div>}

                <p>Medication name:</p>
                <TextInput name="medication_name"
                           placeholder={this.state.formControls.medication_name.placeholder}
                           value={this.state.formControls.medication_name.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.medication_name.touched}
                           valid={this.state.formControls.medication_name.valid}
                />
                {this.state.formControls.medication_name.touched && !this.state.formControls.medication_name.valid &&
                <div className={"error-message row"}> * Name must have at least 3 characters </div>}

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

                <p> Intake interval: </p>
                <TextInput name="intake_interval"
                           placeholder={this.state.formControls.intake_interval.placeholder}
                           value={this.state.formControls.intake_interval.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.intake_interval.touched}
                           valid={this.state.formControls.intake_interval.valid}
                />

                <p> Start time: </p>
                <TextInput name="start_time"
                           placeholder={this.state.formControls.start_time.placeholder}
                           value={this.state.formControls.start_time.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.start_time.touched}
                           valid={this.state.formControls.start_time.valid}
                />

                <p> End time: </p>
                <TextInput name="end_time"
                           placeholder={this.state.formControls.end_time.placeholder}
                           value={this.state.formControls.end_time.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.end_time.touched}
                           valid={this.state.formControls.end_time.valid}
                />

                <p>Doctor name:</p>
                <TextInput name="doctor_name"
                           placeholder={this.state.formControls.doctor_name.placeholder}
                           value={this.state.formControls.doctor_name.value}
                           onChange={this.handleChange}
                           touched={this.state.formControls.doctor_name.touched}
                           valid={this.state.formControls.doctor_name.valid}
                />
                {this.state.formControls.doctor_name.touched && !this.state.formControls.doctor_name.valid &&
                <div className={"error-message row"}> * Name must have at least 3 characters </div>}

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
export default MedicationPlanForm;
