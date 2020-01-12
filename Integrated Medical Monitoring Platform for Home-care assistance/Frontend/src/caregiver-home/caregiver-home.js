import React from 'react';
import BackgroundImg from '../commons/images/future-medicine.jpg';
import NavigationCaregiver from "../navigation-caregiver";
import {
    Container,
    Jumbotron,
} from 'reactstrap';
import SockJsClient from "react-stomp";

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1920px",
    backgroundImage: `url(${BackgroundImg})`
};
const textStyle = {color: 'white',
    textAlign: 'center'
};

class CaregiverHome extends React.Component {

    constructor(props) {
        super(props);
    };

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
        const name = sessionStorage.getItem('name');
        sessionStorage.setItem('table', 'other');
        return (
            <div>
                <NavigationCaregiver />
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>

                        <h3 style={textStyle}>Integrated Medical Monitoring Platform for Home-care assistance</h3>
                        <p>
                        </p>
                        <h1 style={textStyle}>Caregiver: {name}</h1>
                        <hr className="my-2"/>
                        <p>

                        </p>

                    </Container>
                </Jumbotron>

                <SockJsClient url='http://localhost:8888/socket' topics={['/topic/activitymonitor']}
                              onMessage={(msg) => { this.handleMessage(msg); }}
                              ref={ (client) => { this.clientRef = client }} />
            </div>

        )
    };
}

export default CaregiverHome;
