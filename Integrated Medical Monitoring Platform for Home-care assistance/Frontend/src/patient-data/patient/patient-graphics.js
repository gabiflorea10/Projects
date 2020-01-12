import Chart from 'react-google-charts';
import React from "react";
import PatientFormInsert from "./patient-form-insert";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import * as API_PATIENTS from "./api/patient-api"

class PatientGraphics extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            collapseForm: true,
            loadPage: false,
            errorStatus: 0,
            error: null
        };

        this.data = [['Activity', 'Time']]

    }

    getCharts(){
        const patient = sessionStorage.getItem("lastTableClick");
        const date = sessionStorage.getItem("monitorDate");

        return API_PATIENTS.getCharts(patient, date,(result, status, err) => {
            console.log(result);
            if(result !== null && status === 200) {

                console.log(result.activitiesMap)
                console.log(result.activitiesMap.entry)

                const mapData = result.activitiesMap.entry

                for(var {key, value} of mapData){
                    console.log(key)
                    console.log(value)
                    this.data.push([key, value])
                }

                this.forceUpdate();
            } else {
                console.log("An error encountered!");
                this.state.errorStatus = status;
                this.state.error = err;
                this.forceUpdate();
            }
        });

    }


    componentDidMount() {
        this.getCharts();
    }
    render() {

        return (

            <div>
                <Chart
                    width={'600px'}
                    height={'400px'}
                    chartType="PieChart"
                    loader={<div>Loading Chart</div>}
                    data={this.data}
                    options={{
                        title: 'Patient activities',
                        // Just add this option
                        is3D: true,
                    }}
                    rootProps={{'data-testid': '2'}}
                />

                {this.state.errorStatus > 0 &&
                <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>}

            </div>



        );
    }
}

export default PatientGraphics;