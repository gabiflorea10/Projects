import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"
import PatientFormInsert from "./patient-form-insert";

import * as API_PATIENTS from "./api/patient-api"
import NavigationPatient from "../../navigation-patient";

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

];

class PatientInfo extends React.Component {

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

        return API_PATIENTS.getPatientByUsername(username, (result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {
                this.tableData.push({
                    name: result.name,
                    username: result.username,
                    password: result.password,
                    birthdate: result.birthdate,
                    gender: result.gender,
                    medical_record: result.medical_record,
                    caregiver_username: result.caregiver_username
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

    render() {
        let pageSize = 5;
        return (
            <div>
                <NavigationPatient />
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

                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </div>
        );
    };

}

export default PatientInfo;
