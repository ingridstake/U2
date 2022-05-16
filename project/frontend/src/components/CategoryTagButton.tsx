import { Button } from "react-bootstrap";
import { event } from "./Models";
import { useState } from "react";


export const CategoryTagButton = (t_name: string) => {

    const shoot = (name: string) => {
        alert(name);
    }

    return (
        <>
          <button onClick={() => shoot(t_name)}>
              {t_name}
            </button>
        </>
    );
}
