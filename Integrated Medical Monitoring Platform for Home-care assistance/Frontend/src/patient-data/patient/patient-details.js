import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"
import PatientFormInsert from "./patient-form-insert";
import PatientFormUpdate from "./patient-form-update";
import PatientFormDelete from "./patient-form-delete";
import PatientGraphics from "./patient-graphics";

import * as API_PATIENTS from "./api/patient-api"
import NavigationDoctor from "../../navigation-doctor";
import NavigationCaregiver from "../../navigation-caregiver";
import Button from "react-bootstrap/Button";
import TextInput from "./fields/TextInput";
import './patient.css'
import validate from "./validators/patient-validators";


class PatientDetails extends React.Component {

    constructor(props){
        super(props);

        this.toggleForm = this.toggleForm.bind(this);
        this.state = {
            collapseForm: true,
            loadPage: false,
            errorStatus: 0,
            error: null,

            formIsValid: false,

            dateValue: '',

            formControls : {
                monitorDate: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    },
                recommendation: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                }
                }
        };

        this.tableData = [];
        this.tableData1 = [];

        this.handleAnnotationChange = this.handleAnnotationChange.bind(this);
        this.handleSubmitDate = this.handleSubmitDate.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmitRec = this.handleSubmitRec.bind(this);

    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    componentDidMount() {
        this.fetchActivities();
        if(!sessionStorage.getItem('monitorDate').localeCompare('')){

        }
        else{
            this.fetchMonitors();
        }

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
        updatedFormElement.valid = true;

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

    handleAnnotationChange(){
        const activity_id = sessionStorage.getItem('activityId');
        sessionStorage.removeItem('activityId')
        this.changeAnnotation(activity_id);
        this.refresh();

    }

    changeAnnotation(activityId){
        return API_PATIENTS.changeAnnotation(activityId, (result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {
                this.forceUpdate();
            } else {
                console.log("An error encountered!");
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });

    }

    fetchActivities(){
        const patient = sessionStorage.getItem("lastTableClick")

        return API_PATIENTS.getActivities(patient, (result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {
                result.forEach( x => {
                    this.tableData.push({
                        activity_id: x.activityId,
                        name: x.patientName,
                        activity_label: x.activityLabel,
                        hours: x.hours,
                        activity_date: x.activityDate,
                        caregiver_username: x.caregiverUsername,
                        annotation: x.isAnomalous
                    });
                });
                this.forceUpdate();
            } else {
                console.log("An error encountered!");
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });

    }

    fetchMonitors(){
        const patient = sessionStorage.getItem("lastTableClick");
        const date = sessionStorage.getItem('monitorDate');

        return API_PATIENTS.getMonitors(patient, date, (result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {
                result.forEach( x => {
                    this.tableData1.push({
                        name: x.patientName,
                        medication: x.medicationName,
                        intake_date: x.intakeDate,
                        description: x.description
                    });
                });
                this.forceUpdate();
            } else {
                console.log("An error encountered!");
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });

    }


    refresh(){
        this.forceUpdate()
    }

    navBar(){
        const role = sessionStorage.getItem('role');
        if(!role.localeCompare("caregiver"))
            return (<NavigationCaregiver />);
        else{
            return (<NavigationDoctor />);
        }
    }

    handleSubmitDate() {
        sessionStorage.setItem('monitorDate', this.state.formControls.monitorDate.value);
        sessionStorage.removeItem('activityId');
        this.fetchMonitors();
    }

    addRecommendation(patient, description){
        return API_PATIENTS.addRecommendation(patient, description, (result, status, error) => {
            console.log(result);
            if(result !== null && (status === 200 || status ===201)){
                console.log(result);
                this.forceUpdate();
            } else {
                console.log("An error encountered!");
                this.state.errorStatus = status;
                this.state.error = error;
                this.forceUpdate();
            }
        });
    }

    handleSubmitRec(){
        const patient = sessionStorage.getItem("lastTableClick");
        const description = this.state.formControls.recommendation.value;
        this.addRecommendation(patient, description);

    }

    showChart(){
            if(!sessionStorage.getItem('monitorDate').localeCompare('')){
                return(<p></p>);
            }
            else{
                return( <PatientGraphics patientGraphics>
                </PatientGraphics>);
            }
    }


    render() {
        let pageSize = 5;
        const patientName = sessionStorage.getItem('lastTableClick');
        sessionStorage.setItem('table', 'patientDetails');

        const columns = [
            {
                Header:  'Name',
                accessor: 'name',
            },
            {
                Header:  'Activity',
                accessor: 'activity_label',
            },
            {
                Header:  'Hours',
                accessor: 'hours',
            },
            {
                Header:  'Date',
                accessor: 'activity_date',
            },
            {
                Header: 'Caregiver',
                accessor: 'caregiver_username',
            },
            {
                Header: 'Anomalous',
                accessor: 'annotation',
            },
        ];

        const filters = [
            {
                accessor: 'name',
            },
            {
                accessor: 'activity_label',
            },
            {
                accessor: 'hours',
            },
            {
                accessor: 'activity_date',
            },
            {
                accessor: 'caregiver_username',
            },
            {
                accessor: 'annotation',
            },
        ];

        const columns1 = [
            {
                Header:  'Name',
                accessor: 'name',
            },
            {
                Header:  'Medication',
                accessor: 'medication',
            },
            {
                Header:  'Intake date',
                accessor: 'intake_date',
            },
            {
                Header:  'Description',
                accessor: 'description',
            }
        ];

        const filters1 = [
            {
                accessor: 'name',
            },
            {
                accessor: 'medication',
            },
            {
                accessor: 'intake_date',
            },
            {
                accessor: 'description',
            }
        ];

        const textStyle = {color: 'red',
            textAlign: 'center',
            fontSize: '20'
        };


        return (
            <div>
                {this.navBar()}
                <h4>
                    Patient details: {patientName}
                </h4>
                <br/>

                <h5>
                    Recent pacient activities:
                </h5>
                <br/>
                <Row>
                    <Col>
                        <Card body>
                            <Table
                                data={this.tableData}
                                columns={columns}
                                search={filters}
                                pageSize={pageSize}
                            />
                        </Card>
                    </Col>
                </Row>

                <div className='add-date'>
                    <h5 style={textStyle}>
                        &nbsp;&nbsp;Click on the activity you want to change annotation for and press the button: &nbsp;&nbsp;&nbsp;

                    </h5>
                    <form onSubmit={this.handleAnnotationChange}>
                        <button type={"submit"}> Change annotation </button>
                    </form>
                </div>


                <br/>
                <br/>
                <br/>

                <h3>
                    &nbsp; Insert a date to see detailed monitors for patient
                </h3>

                <br/>
                <br/>

                <form onSubmit={this.handleSubmitDate}>

                    <div className='add-date'>
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        &nbsp;
                        <TextInput name="monitorDate"
                                   placeholder={this.state.formControls.monitorDate.placeholder}
                                   value={this.state.formControls.monitorDate.value}
                                   onChange={this.handleChange}
                                   touched={this.state.formControls.monitorDate.touched}
                                   valid={this.state.formControls.monitorDate.valid}
                                   class='textDate'
                        />
                        &nbsp;
                        &nbsp;
                        <button type={"submit"} class='dateButton'>
                            Show
                        </button>

                    </div>
                </form>

                    <br/>
                    <br/>
                    <br/>
                    <br/>

                    {this.showChart()}

                    <br/>
                    <br/>
                    <br/>
                    <br/>


                <h5>
                    Pills intake monitoring:
                </h5>
                <br/>

                <Row>
                    <Col>
                        <Card body>
                            <Table
                                data={this.tableData1}
                                columns={columns1}
                                search={filters1}
                                pageSize={pageSize}
                            />
                        </Card>
                    </Col>
                </Row>

                <br/>
                <br/>
                <br/>
                <br/>

                <h4>
                    &nbsp; Add a recommendation for patient
                </h4>

                <form onSubmit={this.handleSubmitRec}>

                    <div className='add-date'>
                        &nbsp;
                        &nbsp;
                        <TextInput name="recommendation"
                                   placeholder={this.state.formControls.recommendation.placeholder}
                                   value={this.state.formControls.recommendation.value}
                                   onChange={this.handleChange}
                                   touched={this.state.formControls.recommendation.touched}
                                   valid={this.state.formControls.recommendation.valid}
                                   class='textRec'
                        />
                        &nbsp;
                        &nbsp;
                        <button type={"submit"} className='recButton'>
                            Add recommendation
                        </button>

                    </div>
                </form>

                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>

                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}


            </div>
        );
    };

}

export default PatientDetails;
