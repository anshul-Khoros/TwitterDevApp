import React from 'react';
import ReactDOM from 'react-dom';
import LabName from './components/LabName.jsx';
import ShowTimeline from './components/ShowTimeline.jsx';
import './static/scss/styles.scss';

const App = () => {
    return(
        <div>
            <LabName labName="Anshul" />
            <ShowTimeline />
        </div>
    )
}

export default App;