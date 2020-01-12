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

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};

const NavigationCaregiver = () => (
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
                            <NavLink href="/associatedPatients/">Patients</NavLink>
                            <NavLink href="/medicationPlansForPatients/">Medication plans</NavLink>
                            <NavLink href="/recommendationsForPatients/">Recommendations</NavLink>
                            <NavLink href="/">Logout</NavLink>
                        </DropdownItem>


                    </DropdownMenu>
                </UncontrolledDropdown>

            </Nav>
        </Navbar>
    </div>
);

export default NavigationCaregiver;
