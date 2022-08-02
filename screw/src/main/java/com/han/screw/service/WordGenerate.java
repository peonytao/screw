package com.han.screw.service;

import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;

/**
 * 生成Markdown
 *
 * @author han
 * @date 2022/08/02
 */
public class WordGenerate extends GenerateTemplate{
    @Override
    EngineConfig engineConfig() {
        return EngineConfig.builder()
                //生成文件路径
                .fileOutputDir("/Users/han/Downloads")
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.WORD)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker).build();
    }
}
