package com.eventmanagement.eventmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode {
    @Id
    String unique_id;
    @Column (nullable = false)
    int user_id;
}
