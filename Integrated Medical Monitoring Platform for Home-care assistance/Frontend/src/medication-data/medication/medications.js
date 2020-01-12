import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"
import MedicationFormInsert from "./medication-form-insert";
import MedicationFormUpdate from "./medication-form-update";
import MedicationFormDelete from "./medication-form-delete";
import { MDBCard, MDBCardHeader, MDBCardBody, MDBTableEditable } from "mdbreact";

import * as API_MEDICATIONS from "./api/medication-api"
import NavigationDoctor from "../../navigation-doctor";

const columns = [
    {
        Header:  'Name',
        accessor: 'name',
    },
    {
        Header: 'Side effects',
        accessor: 'side_effects',
    },
    {
        Header: 'Dosage',
        accessor: 'dosage',
    },

];

const filters = [
    {
        accessor: 'name',
    },
    {
        accessor: 'side_effects',
    },
    {
        accessor: 'dosage',
    }
];

class Medications extends React.Component {

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
        this.fetchMedications();
    }

    fetchMedications() {
        return API_MEDICATIONS.getMedications((result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {
                result.forEach( x => {
                    this.tableData.push({
                        name: x.name,
                        side_effects: x.side_effects,
                        dosage: x.dosage
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
        sessionStorage.setItem('table', 'other');
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
                                <MedicationFormInsert insertMedication={this.refresh}>
                                </MedicationFormInsert>
                            </div>
                        </Card>
                    </Col>
                    <Col>
                        <Card body>
                            <div>
                                <MedicationFormUpdate updateMedication={this.refresh}>
                                </MedicationFormUpdate>
                            </div>
                        </Card>
                    </Col>
                    <Col>
                        <Card body>
                            <div>
                                <MedicationFormDelete deleteMedication={this.refresh}>
                                </MedicationFormDelete>
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

export default Medications;
