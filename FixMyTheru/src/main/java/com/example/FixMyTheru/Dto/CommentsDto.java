package com.example.FixMyTheru.Dto;

import com.example.FixMyTheru.Models.Comments;
import com.example.FixMyTheru.Models.RegisterDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

        private String Comments;
        private String username;
        private LocalDateTime Date;
        private LocalDateTime time;

}
