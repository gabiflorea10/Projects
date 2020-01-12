import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"
import CaregiverFormInsert from "./caregiver-form-insert";
import CaregiverFormUpdate from "./caregiver-form-update";
import CaregiverFormDelete from "./caregiver-form-delete";

import * as API_CAREGIVERS from "./api/caregiver-api"
import NavigationDoctor from "../../navigation-doctor";

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
        Header: 'Role',
        accessor: 'role',
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
        accessor: 'role',
    }
];

class Caregivers extends React.Component {

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
        this.fetchCaregivers();
    }

    fetchCaregivers() {
        return API_CAREGIVERS.getCaregivers((result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {
                result.forEach( x => {
                    this.tableData.push({
                        name: x.name,
                        username: x.username,
                        password: x.password,
                        birthdate: x.birthdate,
                        gender: x.gender,
                        role: x.role
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

    render() {
        let pageSize = 5;
        return (
            <div>
                <NavigationDoctor />
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

                <Row>
                    <Col>
                        <Card body>
                            <div>
                                <CaregiverFormInsert insertCaregiver={this.refresh}>

                                </CaregiverFormInsert>
                            </div>
                        </Card>
                    </Col>
                    <Col>
                        <Card body>
                            <div>
                                <CaregiverFormUpdate updateCaregiver={this.refresh}>

                                </CaregiverFormUpdate>
                            </div>
                        </Card>
                    </Col>
                    <Col>
                        <Card body>
                            <div>
                                <CaregiverFormDelete deleteCaregiver={this.refresh}>

                                </CaregiverFormDelete>
                            </div>
                        </Card>
                    </Col>
                </Row>

                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </div>
        );
    };

}

export default Caregivers;
