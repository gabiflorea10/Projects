import React, { Component } from 'react';

export class About extends Component {
  static displayName = About.name;

  render() {
      return (
          <div style={{ marginLeft: '20%', marginRight: '20%' }}>
        <h1>About</h1>

            <p>This tasks manager application has been created by FLOREA Gabriel Alin.</p>
            <br />
            <p>The frontent is implemented using ReactJs, the backend is created with .NET core and for the database I used a SQL database.</p>



      </div>
    );
  }
}
