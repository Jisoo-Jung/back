package com.app.ggumteo.service.audition;

import com.app.ggumteo.domain.audition.AuditionDTO;
import com.app.ggumteo.domain.audition.AuditionVO;
import com.app.ggumteo.domain.post.PostVO;
import com.app.ggumteo.repository.audition.AuditionDAO;
import com.app.ggumteo.repository.post.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AuditionServiceImpl implements AuditionService {

    private final AuditionDAO auditionDAO;
    private final PostDAO postDAO;
    private final AuditionVO auditionVO;

    @Override
    public void write(AuditionDTO auditionDTO) {
        PostVO postVO = new PostVO();
        postVO.setPostTitle(auditionDTO.getPostTitle());
        postVO.setPostContent(auditionDTO.getPostContent());
        postVO.setPostType(auditionDTO.getPostType());
        postVO.setMemberId(auditionDTO.getMemberId());

        postDAO.save(postVO);
        Long postId = postVO.getId();

        // AuditionDTO의 데이터를 AuditionVO로 복사
        auditionVO.setId(postId);
        auditionVO.setAuditionField(auditionDTO.getAuditionField());
        auditionVO.setAuditionCareer(auditionDTO.getAuditionCareer());
        auditionVO.setExpectedAmount(auditionDTO.getExpectedAmount());
        auditionVO.setServiceStartDate(auditionDTO.getServiceStartDate());
        auditionVO.setAuditionDeadLine(auditionDTO.getAuditionDeadLine());
        auditionVO.setAuditionPersonnel(auditionDTO.getAuditionPersonnel());
        auditionVO.setAuditionLocation(auditionDTO.getAuditionLocation());
        auditionVO.setAuditionBackground(auditionDTO.getAuditionBackground());
        auditionVO.setAuditionCategory(auditionDTO.getAuditionCategory());
        auditionVO.setFileContent(auditionDTO.getFileContent());
        auditionVO.setAuditionStatus(auditionDTO.getAuditionStatus());

        // 완전히 설정된 auditionVO를 저장
        auditionDAO.save(auditionVO);

        auditionDTO.setId(postId);
    }



}