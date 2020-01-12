import React from 'react'
import logo from './commons/images/icon.png';

import {
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavLink,
    UncontrolledDropdown
} from 'reactstrap';
import MedicationPlans from "./medication-plan-data/medication-plan/medication-plan";

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};

const NavigationPatient = () => (
    <div>
        <Navbar color="dark" light expand="md">
            <NavbarBrand>
                <img src={logo} width={"60"}
                     height={"45"} />
            </NavbarBrand>
            <Nav className="mr-auto" navbar>

                <UncontrolledDropdown nav inNavbar>
                    <DropdownToggle style={textStyle} nav caret>
                        Menu
                    </DropdownToggle>
                    <DropdownMenu right >

                        <DropdownItem>
                            <NavLink href="/myMedicationPlans/">Medication Plans</NavLink>
                            <NavLink href="/myAccount_patient/">My account</NavLink>
                            <NavLink href="/">Logout</NavLink>
                        </DropdownItem>

                    </DropdownMenu>
                </UncontrolledDropdown>

            </Nav>
        </Navbar>
    </div>
);

export default NavigationPatient;
