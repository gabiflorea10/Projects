import React, { Component } from "react";
import { FormControl, InputGroup, Button, Form, Modal } from "react-bootstrap";

export class NewTodoModal extends Component {

    constructor(props) {
        super(props)
        this.state = { show: false, modalTodo:'' };
      
        this.handleShow = this.handleShow.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.handleChange = this.handleChange.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);

    }

    handleShow() {
        this.setState({
            show: true
        });

      
    }

    handleClose() {
        this.setState({
            show: false
        });
    }

    handleChange(event) {

        this.setState({
            modalTodo: event.target.value
        });


    }

    handleFormSubmit() {
        this.props.addTodo(this.state.modalTodo)
        this.setState({
            modalTodo: ''
        });
}

    render() {
        return (
            <div>
                <Button onClick={this.handleShow} className='btn btn-success' style={{float: 'right'}}>New Todo</Button>

                <Modal show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Add a new todo</Modal.Title>
                    </Modal.Header>
                    <Form onSubmit={event => {
                        event.preventDefault();
                        this.handleFormSubmit()
                    }}>
                        <Modal.Body>
                            <InputGroup>
                                <FormControl value={this.state.modalTodo} onChange={this.handleChange}
                                />
                            </InputGroup>

                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={this.handleClose}>
                                Close
            </Button>
                            <Button type='submit' variant="primary" onClick={this.handleClose}>
                                Save
            </Button>
                        </Modal.Footer>
                    </Form>
                </Modal>
            </div>



        )
    }
}