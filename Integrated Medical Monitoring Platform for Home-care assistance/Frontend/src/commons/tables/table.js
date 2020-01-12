import React, {Component} from "react";
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import Field from "./fields/Field";
import {Col, Row} from "react-bootstrap";
import './fields/styles/table-style.css'
import PatientDetails from "../../patient-data/patient/patient-details";
import {Route} from "react-router";
import {Redirect} from "react-router-dom";

class Table extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: props.data,
            columns: props.columns,
            search: props.search,
            filters: [],
            getTrPropsFunction: props.getTrProps,
            pageSize: props.pageSize || 10,
        };
    }

    search() {

    }

    filter(data) {
        let accepted = true;

        this.state.filters.forEach(val => {
            if (String(val.value) === "") {
                accepted = true;
            }

            if (!String(data[val.accessor]).includes(String(val.value)) && !String(val.value).includes(String(data[val.accessor]))) {
                accepted = false;
            }
        });

        return accepted;
    }

    handleChange(value, index, header) {
        if (this.state.filters === undefined)
            this.setState({filters: []});

        this.state.filters[index] = {
            value: value.target.value,
            accessor: header
        };

        this.forceUpdate();
    }


    getTRPropsType(state, rowInfo) {
        if (rowInfo) {
            return {
                style: {
                    textAlign: "center",
            },
                onClick: () => {
                    if(!sessionStorage.getItem('role').localeCompare('doctor')){
                        sessionStorage.setItem('lastTableClick', state.data[rowInfo.index].name);
                        sessionStorage.setItem('activityId', state.data[rowInfo.index].activity_id)
                        if(!sessionStorage.getItem('table').localeCompare('patients')){
                            window.location.href="/patientDetails/"
                        }

                    }
                }
            };
        }
        else
            return {};
    }



    render() {
        let data = this.state.data ? this.state.data.filter(data => this.filter(data)) : [];

        return (
            <div>
                <Row>
                    {
                        this.state.search.map((header, index) => {
                            return (
                                <Col key={index}>
                                    <div >
                                        <Field id={header.accessor} label={header.accessor}
                                               onChange={(e) => this.handleChange(e, index, header.accessor)}/>
                                    </div>
                                </Col>
                            )
                        })
                    }
                </Row>
                <Row>
                    <Col>
                        <ReactTable
                            data={data}
                            resolveData={data => data.map(row => row)}
                            columns={this.state.columns}
                            getTrProps={this.getTRPropsType}
                            defaultPageSize={this.state.pageSize}

                        />
                    </Col>
                </Row>
            </div>
        )
    }
}

export default Table;
