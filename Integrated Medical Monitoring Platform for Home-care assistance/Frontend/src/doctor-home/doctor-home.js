import React from 'react';
import BackgroundImg from '../commons/images/future-medicine.jpg';
import NavigationDoctor from "../navigation-doctor";
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

class DoctorHome extends React.Component {

    constructor(props) {
        super(props);
    };


    render() {
        const name = sessionStorage.getItem('name');
        sessionStorage.setItem('table', 'other');
        return (
            <div>
                <NavigationDoctor />
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>

                        <h3 style={textStyle}>Integrated Medical Monitoring Platform for Home-care assistance</h3>
                        <p>
                        </p>
                        <h1 style={textStyle}>Doctor: {name}</h1>
                        <hr className="my-2"/>
                        <p>

                        </p>

                    </Container>
                </Jumbotron>

            </div>
        )
    };
}

export default DoctorHome;
