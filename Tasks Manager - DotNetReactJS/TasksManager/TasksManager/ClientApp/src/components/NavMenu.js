import React, { Component } from 'react';
import { Collapse, Container, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink } from 'reactstrap';
import { Link, Redirect } from 'react-router-dom';
import './NavMenu.css';

export class NavMenu extends Component {
  static displayName = NavMenu.name;

  constructor (props) {
    super(props);

    this.toggleNavbar = this.toggleNavbar.bind(this);
    this.handleLogoutClick = this.handleLogoutClick.bind(this);
    this.state = {
      collapsed: true
    };
  }


  toggleNavbar () {
    this.setState({
      collapsed: !this.state.collapsed
    });
    }

    handleLogoutClick() {
        this.props.handleLogout()
        return <Redirect to="/"/>
    }

    render() {
        const login = this.props.login
        let additionalLink;
        let logout;
        let signupMode = this.props.signupModePar;
        let about;
        if (login) {
            additionalLink = <NavItem>
                <NavLink tag={Link} className="text-dark" to="/todos">Todos</NavLink>
            </NavItem>
            logout = <NavItem>
                <button className='btn btn-primary' onClick={this.handleLogoutClick}>Logout</button>
            </NavItem>
        }
        else if(!signupMode){
            additionalLink = <NavItem>
                <NavLink tag={Link} className="text-dark" to="/">Login</NavLink>
            </NavItem>
        }

        if (signupMode) {
            about = <div>
                    </div>
        }
        else {
            about = <NavItem>
                        <NavLink tag={Link} className="text-dark" to="/about">About</NavLink>
                    </NavItem>  
        }

    return (
      <header>
        <Navbar className="navbar-expand-sm navbar-toggleable-sm ng-white border-bottom box-shadow mb-3" light>
          <Container>
            <NavbarBrand >Tasks Manager</NavbarBrand>
            <NavbarToggler onClick={this.toggleNavbar} className="mr-2" />
            <Collapse className="d-sm-inline-flex flex-sm-row-reverse" isOpen={!this.state.collapsed} navbar>
                <ul className="navbar-nav flex-grow">
                    <div>{additionalLink}
                    </div>
                            {about}
                            <div>{logout}
                            </div>
                        </ul>
                        
                    </Collapse>
                    
          </Container>
        </Navbar>
      </header>
    );
  }
}
