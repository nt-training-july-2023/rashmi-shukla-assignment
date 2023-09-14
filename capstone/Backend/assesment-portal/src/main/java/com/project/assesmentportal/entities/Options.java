package com.project.assesmentportal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Options class containing all the options.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Options {
    /**
     * The optionI of the question.
     */
    private String optionI;
    /**
     * The optionII of the question.
     */
    private String optionII;
    /**
     * The optionIII of the question.
     */
    private String optionIII;
    /**
     * The optionIV of the question.
     */
    private String optionIV;
}
