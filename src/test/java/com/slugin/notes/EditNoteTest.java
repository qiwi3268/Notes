package com.slugin.notes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-notes-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-notes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EditNoteTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validEditNoteTest() throws Exception {
        this.mockMvc.perform(post("/note/1")
                .param("header", "")
                .param("content", "\n\nedited text"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//div[@id='note-list']/div[@data-id='1']/a/input[@placeholder='edited text']").exists())
                .andExpect(xpath("//div[@id='note-list']/div").nodeCount(4));
    }

    @Test
    public void wrongEditNoteTest() throws Exception {
        this.mockMvc.perform(post("/note/2")
                .param("header", "    ")
                .param("content", "\n\n"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Please fill the header or text")))
                .andExpect(xpath("//input[@value='    ']").exists())
                .andExpect(xpath("//textarea[@id='validationTextarea']").string("\n\n"));
    }
}
