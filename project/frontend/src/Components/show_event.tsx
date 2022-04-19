import React from "react";
import test from "../test.json";
import './style/show_events.css';
import Table from "react-bootstrap/Table";


function JsonDataDisplay() {
    const DisplayData=test.map(
        (test)=>{
            return(
                <tr key={test.id}>
                    <td>{test.id}</td>
                    <td>{test.name}</td>
                    <td>{test.city}</td>
                </tr>
            )
        }
    )
 
    return(
        <>
            <Table >
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
