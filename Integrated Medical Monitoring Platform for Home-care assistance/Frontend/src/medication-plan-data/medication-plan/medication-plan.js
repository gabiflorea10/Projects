import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"
import { MDBCard, MDBCardHeader, MDBCardBody, MDBTableEditable } from "mdbreact";

import * as API_MEDICATION_PLANS from "./api/medication-plan-api"
import NavigationPatient from "../../navigation-patient";
import NavigationCaregiver from "../../navigation-caregiver";
import NavigationDoctor from "../../navigation-doctor";
import MedicationPlanForm from "../../medication-data/medication/medication-plan-form";
import SockJsClient from "react-stomp";

const columns = [
    {
        Header:  'Patient name',
        accessor: 'patient_name',
    },
    {
        Header:  'Medication name',
        accessor: 'medication_name',
    },
    {
        Header: 'Side effects',
        accessor: 'side_effects',
    },
    {
        Header: 'Dosage',
        accessor: 'dosage',
    },
    {
        Header: 'Intake interval',
        accessor: 'intake_interval',
    },
    {
        Header: 'Start time',
        accessor: 'start_time',
    },
    {
        Header: 'End time',
        accessor: 'end_time',
    },
    {
        Header: 'Doctor name',
        accessor: 'doctor_name',
    }

];

const filters = [
    {
        accessor: 'patient_name',
    },
    {
        accessor: 'medication_name',
    },
    {
        accessor: 'side_effects',
    },
    {
        accessor: 'dosage',
    },
    {
        accessor: 'intake_interval',
    },
    {
        accessor: 'start_time',
    },
    {
        accessor: 'end_time',
    },
    {
        accessor: 'doctor_name',
    }
];

class MedicationPlans extends React.Component {
    constructor(props){
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.state = {
            collapseForm: true,
            loadPage: false,
            errorStatus: 0,
            error: null
        };

        this.tableData = [];
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    componentDidMount() {
        this.fetchMedicationPlans();
    }

    fetchMedicationPlans() {
        const username = sessionStorage.getItem('username');
        const role = sessionStorage.getItem('role');

        if(!role.localeCompare("patient")) {
            return API_MEDICATION_PLANS.getMedicationPlansByUsername(username, (result, status, err) => {
                console.log(result);
                if (result !== null && status === 200) {
                    result.forEach(x => {
                        this.tableData.push({
                            patient_name: x.patient_name,
                            medication_name: x.medication_name,
                            side_effects: x.side_effects,
                            dosage: x.dosage,
                            intake_interval: x.intake_interval,
                            start_time: x.start_time,
                            end_time: x.end_time,
                            doctor_name: x.doctor_name,
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
        else if(!role.localeCompare("caregiver")) {
            return API_MEDICATION_PLANS.getMedicationPlansByCaregiverUsername(username, (result, status, err) => {
                console.log(result);
                if (result !== null && status === 200) {
                    result.forEach(x => {
                        this.tableData.push({
                            patient_name: x.patient_name,
                            medication_name: x.medication_name,
                            side_effects: x.side_effects,
                            dosage: x.dosage,
                            intake_interval: x.intake_interval,
                            start_time: x.start_time,
                            end_time: x.end_time,
                            doctor_name: x.doctor_name,
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
        else {
            return API_MEDICATION_PLANS.getMedicationPlanViews((result, status, err) => {
                console.log(result);
                if (result !== null && status === 200) {
                    result.forEach(x => {
                        this.tableData.push({
                            patient_name: x.patient_name,
                            medication_name: x.medication_name,
                            side_effects: x.side_effects,
                            dosage: x.dosage,
                            intake_interval: x.intake_interval,
                            start_time: x.start_time,
                            end_time: x.end_time,
                            doctor_name: x.doctor_name,
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
    }

    navBar(){
        const role = sessionStorage.getItem('role');
        if(!role.localeCompare("caregiver")) {
            return (<NavigationCaregiver/>);
        }
        else if(!role.localeCompare("patient")){
            return (<NavigationPatient />);
        }
        else{
            return (<NavigationDoctor />);
        }
    }


    refresh(){
        this.forceUpdate()
    }

    insertMedicationPlan(){
        console.log("Step0");

        const role = sessionStorage.getItem("role");
        if(!role.localeCompare("doctor")){
            console.log("Step0");
            return (
                <Row>
                    <Col>
                        <Card body>
                            <div>
                                <MedicationPlanForm insertMedicationPlan={this.refresh}>
                                </MedicationPlanForm>
                            </div>
                        </Card>
                    </Col>
                </Row>
            );
        }

    }

    handleMessage(msg){
        if(!sessionStorage.getItem("role").localeCompare("caregiver")){
            let caregiver_username = msg.caregiver_username;
            if (!caregiver_username.localeCompare(sessionStorage.getItem("username"))){
                let message = msg.patient_name + " might have a problem: " + msg.activity_label + " => " + msg.hours.toFixed(2) + " hours.";
                console.log(msg);
                alert(message);
            }
        }
    }

    render() {
        let pageSize = 5;
        sessionStorage.setItem('table', 'other');
        return (
            <div>
                {this.navBar()}
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
                {this.insertMedicationPlan()}
                <SockJsClient url='http://localhost:8888/socket' topics={['/topic/activitymonitor']}
                              onMessage={(msg) => { this.handleMessage(msg); }}
                              ref={ (client) => { this.clientRef = client }} />
                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </div>
        );
    };

}

export default MedicationPlans;
