import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    ships: []
  };

  async componentDidMount() {
    const response = await fetch('/ships');
    const body = await response.json();
    this.setState({ ships: body, isLoading: false });
  }

  render() {
    const {ships, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Ship List</h2>
            {ships.map(ship =>
              <div key={ship.id}>
                {ship.grossTonnage}
                {ship.shipType}
                {ship.iceClass}
                {ship.flagCountry}
              </div>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;
