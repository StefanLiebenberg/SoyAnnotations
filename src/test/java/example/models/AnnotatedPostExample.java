package example.models;


import org.slieb.soy.annotations.Soy;

import java.util.List;

@Soy
@Soy.Template("templates.AnnotatedPostExample")
public class AnnotatedPostExample {

    @Soy.Field("User")
    public AnnotatedUserExample user;

    @Soy.Field("Comments")
    public List<AnnotatedCommentExample> comments;
}
