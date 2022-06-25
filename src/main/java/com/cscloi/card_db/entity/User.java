//Keith Geneva
//Jonathan Rubio
//Bishoy Soliman Hanna
package com.cscloi.card_db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String user_id;
    private String user_name;

    /**
     * Checks to see if the user is valid.
     * @return True if value, otherwise returns false.
     */
    @JsonIgnore
    public boolean isValid() {
        if ((getUser_id() == null || getUser_id().isEmpty())) {
            return false;
        }
        if ((getUser_name() == null) || (getUser_name().isEmpty())) {
            return false;
        }
        return true;
    }


}