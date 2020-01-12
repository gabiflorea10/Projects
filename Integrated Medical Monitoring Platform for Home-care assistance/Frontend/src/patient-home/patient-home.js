import React from 'react';
import BackgroundImg from '../commons/images/future-medicine.jpg';
import NavigationPatient from "../navigation-patient";
import {
    Container,
    Jumbotron,
} from 'reactstrap';

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

class PatientHome extends React.Component {

    constructor(props) {
        super(props);
    };


    render() {
        const name = sessionStorage.getItem('name');

        return (
            <div>
                <NavigationPatient />
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>

                        <h3 style={textStyle}>Integrated Medical Monitoring Platform for Home-care assistance</h3>
                        <p>
                        </p>
                        <h1 style={textStyle}>Patient: {name}</h1>
                        <hr className="my-2"/>
                        <p>

                        </p>

                    </Container>
                </Jumbotron>

            </div>
        )
    };
}

export default PatientHome;
