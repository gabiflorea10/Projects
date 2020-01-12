import React from 'react';
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Card, Col, Row} from 'reactstrap';
import Table from "../../commons/tables/table"

import * as API_RECOMMENDATIONS from "./api/recommendation-api"
import NavigationCaregiver from "../../navigation-caregiver";
import NavigationDoctor from "../../navigation-doctor";

const columns = [
    {
        Header:  'Patient name',
        accessor: 'patient_name',
    },
    {
        Header:  'Recommendation',
        accessor: 'recommendation',
    }
];

const filters = [
    {
        accessor: 'patient_name',
    },
    {
        accessor: 'recommendation',
    }
];

class Recommendation extends React.Component {
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
        this.fetchRecommendations();
    }

    fetchRecommendations() {
        const username = sessionStorage.getItem('username');
        const role = sessionStorage.getItem('role');

        if(!role.localeCompare("caregiver")) {
            return API_RECOMMENDATIONS.getRecommendations(username, (result, status, err) => {
                console.log(result);
                if (result !== null && status === 200) {
                    result.forEach(x => {
                        this.tableData.push({
                            patient_name: x.patientName,
                            recommendation: x.description,
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
        else{
            return (<NavigationDoctor />);
        }
    }


    refresh(){
        this.forceUpdate()
    }

    render() {
        let pageSize = 5;
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
                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </div>
        );
    };

}

export default Recommendation;
