import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"
import PatientFormInsert from "./patient-form-insert";
import PatientFormUpdate from "./patient-form-update";
import PatientFormDelete from "./patient-form-delete";

import * as API_PATIENTS from "./api/patient-api"
import NavigationDoctor from "../../navigation-doctor";
import NavigationCaregiver from "../../navigation-caregiver";
import SockJsClient from "react-stomp";

const columns = [
    {
        Header:  'Name',
        accessor: 'name',
    },
    {
        Header:  'Username',
        accessor: 'username',
    },
    {
        Header:  'Password',
        accessor: 'password',
    },
    {
        Header:  'Birthdate',
        accessor: 'birthdate',
    },
    {
        Header: 'Gender',
        accessor: 'gender',
    },
    {
        Header: 'Medical record',
        accessor: 'medical_record',
    },
    {
        Header: 'Caregiver username',
        accessor: 'caregiver_username',
    },

];

const filters = [
    {
        accessor: 'name',
    },
    {
        accessor: 'username',
    },
    {
        accessor: 'password',
    },
    {
        accessor: 'birthdate',
    },
    {
        accessor: 'gender',
    },
    {
        accessor: 'medical_record',
    },
    {
        accessor: 'caregiver_username',
    }
];

class Patients extends React.Component {

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
        this.fetchPatients();
    }

    fetchPatients() {
        const username = sessionStorage.getItem('username');
        const role = sessionStorage.getItem('role');
        return API_PATIENTS.getPatients(username, role,(result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {
                result.forEach( x => {
                    this.tableData.push({
                        name: x.name,
                        username: x.username,
                        password: x.password,
                        birthdate: x.birthdate,
                        gender: x.gender,
                        medical_record: x.medical_record,
                        caregiver_username: x.caregiver_username
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


    insertForm(){
        const role = sessionStorage.getItem('role');
        if(role.localeCompare("caregiver"))
        return (
            <Row>
                <Col>
                    <Card body>
                        <div>
                            <PatientFormInsert insertPatient={this.refresh}>

                            </PatientFormInsert>
                        </div>
                    </Card>
                </Col>
                <Col>
                    <Card body>
                        <div>
                            <PatientFormUpdate updatePatient={this.refresh}>

                            </PatientFormUpdate>
                        </div>
                    </Card>
                </Col>
                <Col>
                    <Card body>
                        <div>
                            <PatientFormDelete deletePatient={this.refresh}>

                            </PatientFormDelete>
                        </div>
                    </Card>
                </Col>
            </Row>);

    }

    render() {
        let pageSize = 5;
        sessionStorage.setItem('table', 'patients');
        sessionStorage.setItem('monitorDate', '')
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
                {this.insertForm()}
                <SockJsClient url='http://localhost:8888/socket' topics={['/topic/activitymonitor']}
                              onMessage={(msg) => { this.handleMessage(msg); }}
                              ref={ (client) => { this.clientRef = client }} />
                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </div>
        );
    };

}

export default Patients;
