import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Home from './home/home';

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import Medications from "./medication-data/medication/medications";
import Caregivers from "./caregiver-data/caregiver/caregivers";
import Patients from "./patient-data/patient/patients";
import DoctorHome from "./doctor-home/doctor-home";
import CaregiverHome from "./caregiver-home/caregiver-home";
import PatientHome from "./patient-home/patient-home";
import MedicationPlans from "./medication-plan-data/medication-plan/medication-plan";
import PatientInfo from "./patient-data/patient/patient-info";
import Recommendation from "./recommendation-data/recommendation/recommendation";
import PatientDetails from './patient-data/patient/patient-details';

let enums = require('./commons/constants/enums');

class App extends React.Component {

  render() {

    return (
        <div >
          <Router>
            <div>
              <Switch>

                <Route
                    exact
                    path='/'
                    render={() => <Home/>}
                />

                <Route
                    exact
                    path='/patient'
                    render={() => <Patients/>}
                />

                <Route
                    exact
                    path='/user'
                    render={() => <Caregivers/>}
                />

                <Route
                    exact
                    path='/medication'
                    render={() => <Medications/>}
                />

                <Route
                    exact
                    path='/doctorHome'
                    render={() => <DoctorHome/>}
                />

                <Route
                    exact
                    path='/caregiverHome'
                    render={() => <CaregiverHome/>}
                />

                <Route
                    exact
                    path='/patientHome'
                    render={() => <PatientHome/>}
                />

                <Route
                    exact
                    path='/myMedicationPlans'
                    render={() => <MedicationPlans/>}
                />

                <Route
                    exact
                    path='/myAccount_patient'
                    render={() => <PatientInfo/>}
                />

                <Route
                    exact
                    path='/associatedPatients'
                    render={() => <Patients/>}
                />

                <Route
                    exact
                    path='/medicationPlansForPatients'
                    render={() => <MedicationPlans/>}
                />

                <Route
                    exact
                    path='/medicationPlansDoctor'
                    render={() => <MedicationPlans/>}
                />

                <Route
                    exact
                    path='/recommendationsForPatients'
                    render={() => <Recommendation/>}
                />

                <Route
                    exact
                    path='/patientDetails'
                    render={() => <PatientDetails/>}
                />


                {/*Error*/}
                <Route
                    exact
                    path='/error'
                    render={() => <ErrorPage/>}
                />

                <Route render={() =><ErrorPage/>} />
              </Switch>
            </div>
          </Router>
        </div>
    )
  };
}

export default App
