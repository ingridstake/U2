import React from "react";
import test from "./test.json";
import './show_events.css';
import Table from "react-bootstrap/Table";

/*
function EventList() {
    const listItems = test.map((event) =>
    <li>{event}</li>
    );
    return (
        <ul>{listItems}</ul>
    );
}
*/

function JsonDataDisplay(){
    const DisplayData=test.map(
        (test)=>{
            return(
                <tr>
                    <td>{test.id}</td>
                    <td>{test.name}</td>
                    <td>{test.city}</td>
                </tr>
            )
        }
    )
 
    return(
        <>
        <Table striped bordered hover>
                <thead>
                    <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>City</th>
                    </tr>
                </thead>
                <tbody>
                 
                    {DisplayData}
                    
                </tbody>
        </Table> 
        
        </>
    );
 }

export default JsonDataDisplay;
