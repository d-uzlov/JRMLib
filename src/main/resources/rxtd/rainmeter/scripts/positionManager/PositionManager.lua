--- int -> { measure, values... }
local tMeasures = { { 'measure1', 'measureX10' }, { 'measure2', 'measureX20' } }
--- int -> { baseMeter, meterGroup, meterX, optionNameX, ... }
local tMeters = { { 'base1', 'group1', 'meter1', 'MeasureName' }, { 'base2', 'group2', 'meter2', 'MeasureName' } }
--- [ x1, y1, x2, y2, ... ]
local tPlaceMap = { 11, 11, 22, 22 }

--- int: count of elements in tMeasures
local nMeasureCount
--- int: count of elements in tMeters
local nMeterCount
--- int: tMeasures - 1 (number of optional values in tMeasures)
local nValuesCount

--- Indicated whether options has been read correctly.
local bError = false
--- Indicated whether some external resource has requested update.
local bNeedUpdate = true

--- table: measureStringValue : string -> assignedMeterIndex : int
local tMap = {}
--- int: currently used for generating indices for tMeters
local nMapIndex = 1

function Reset()
    bNeedUpdate = true
end

--- Move all base meters to (0,0) and hide them.
local function hideAll()
    for _, v in pairs(tMeters) do
        -- I use 'SetOptionGroup Hidden' because with 'HideMeterGroup' skin bounds are not updated
        -- It probably happens because neither :SetX nor HideMeterGroup don't cause reload,
        -- and bounds recalculating probably only happen when meter is reloaded
        v[1]:SetX(0)
        v[1]:SetY(0)
        SKIN:Bang("!SetOptionGroup", v[2], "Hidden", "1")
    end
end

--- Read and parse 'Measures' option.
local function readMeasures()
    local array = SELF:GetOption('Measures')
    if (array ~= '') then
        local tmp = loadstring('return ' .. array);
        if (tmp ~= nil) then
            tMeasures = tmp()
        else
            bError = true
            SKIN:Bang('!Log', 'Measures are incorrect', 'Error')
            return
        end
    else
        bError = true
        SKIN:Bang('!Log', 'Measures are missing', 'Error')
        return
    end
    nMeasureCount = #tMeasures
    if (nMeasureCount == 0) then
        bError = true
        SKIN:Bang('!Log', 'Measures count is zero', 'Error')
        return
    end

    local tMeasuresLocal = tMeasures
    local tMeasureInfo = tMeasuresLocal[1]
    if (tMeasureInfo == nil or type(tMeasureInfo) ~= 'table') then
        bError = true
        SKIN:Bang('!Log', 'Measure ' .. 1 .. ' is incorrect', 'Error')
        return
    end
    local nMeasureInfoSize = #tMeasureInfo
    nValuesCount = nMeasureInfoSize - 1
    for i = 1, nMeasureCount, 1 do
        tMeasureInfo = tMeasuresLocal[i]
        if (tMeasureInfo == nil or type(tMeasureInfo) ~= 'table' or #tMeasureInfo ~= nMeasureInfoSize) then
            bError = true
            SKIN:Bang('!Log', 'Measure ' .. i .. ' is incorrect', 'Error')
            return
        end
        for i = 1, nMeasureInfoSize do
            local sValue = tMeasureInfo[1]
            if (sValue == nil or type(sValue) ~= 'string') then
                bError = true
                SKIN:Bang('!Log', 'Measure ' .. i .. ' is incorrect', 'Error')
                return
            end
        end

        local measure = SKIN:GetMeasure(tMeasureInfo[1])
        if (measure == nil) then
            bError = true
            SKIN:Bang('!Log', 'Measure "' .. tMeasureInfo[1] .. '" does not exist')
            return
        end
        tMeasuresLocal[i][1] = measure
    end
end

--- Read and parse 'Meters' option.
local function readMeters()
    local array = SELF:GetOption('Meters')
    if (array ~= '') then
        local tmp = loadstring('return ' .. array);
        if (tmp ~= nil) then
            tMeters = tmp()
        else
            bError = true
            SKIN:Bang('!Log', 'Meters are incorrect', 'Error')
            return
        end
    else
        bError = true
        SKIN:Bang('!Log', 'Meters are missing', 'Error')
        return
    end
    nMeterCount = #tMeters
    if (nMeterCount == 0) then
        bError = true
        SKIN:Bang('!Log', 'Meters count is zero', 'Error')
        return
    end

    local tMetersLocal = tMeters
    local tMeterInfo = tMetersLocal[1]
    if (tMeterInfo == nil or type(tMeterInfo) ~= 'table') then
        bError = true
        SKIN:Bang('!Log', 'Meter ' .. 1 .. ' is incorrect', 'Error')
        return
    end
    local nMeterInfoSize = #tMeterInfo
    if (nMeterInfoSize % 2 ~= 0) then
        bError = true
        SKIN:Bang('!Log', 'Meter option count is not divisible by 2', 'Error')
        return
    end
    if ((nMeterInfoSize - 2) / 2 ~= #(tMeasures[1]) - 1) then
        bError = true
        SKIN:Bang('!Log', 'Meter option count does not match measure values count', 'Error')
        return
    end
    for i = 1, nMeterCount, 1 do
        tMeterInfo = tMetersLocal[i]
        if (tMeterInfo == nil or type(tMeterInfo) ~= 'table' or #tMeterInfo ~= nMeterInfoSize) then
            bError = true
            SKIN:Bang('!Log', 'Meter ' .. i .. ' is incorrect', 'Error')
            return
        end
        for i = 1, nMeterInfoSize do
            local sValue = tMeterInfo[1]
            if (sValue == nil or type(sValue) ~= 'string') then
                bError = true
                SKIN:Bang('!Log', 'Meter ' .. i .. ' is incorrect', 'Error')
                return
            end
        end

        local meter = SKIN:GetMeter(tMeterInfo[1])
        if (meter == nil) then
            bError = true
            SKIN:Bang('!Log', 'Meter "' .. tMeterInfo[1] .. '" does not exist. Aborting')
            return
        end
        tMeters[i][1] = meter
    end
end

--- Read and parse 'Places' option.
local function readPlaces()
    local array = SELF:GetOption('Places')
    if (array ~= '') then
        local tmp = loadstring('return ' .. array);
        if (tmp ~= nil) then
            tPlaceMap = tmp()
        else
            bError = true
            SKIN:Bang('!Log', 'Places are incorrect', 'Error')
            return
        end
    else
        bError = true
        SKIN:Bang('!Log', 'Places are missing', 'Error')
        return
    end
    local nPlacesCount = #tPlaceMap
    if (nPlacesCount == 0) then
        bError = true
        SKIN:Bang('!Log', 'Places count is zero', 'Error')
        return
    end

    if (nPlacesCount % 2 ~= 0) then
        bError = true
        SKIN:Bang('!Log', 'Places count is not divisible by 2', 'Error')
        return
    end

    local tPlacesLocal = tPlaceMap
    for i = 1, nPlacesCount, 1 do
        if (tPlacesLocal [i] == nil or type(tPlacesLocal [i]) ~= 'number') then
            bError = true
            SKIN:Bang('!Log', 'Place ' .. i .. ' is incorrect', 'Error')
            return
        end
    end
end

function Initialize()
    readMeasures()
    readMeters()
    readPlaces()

    if (bError) then
        return
    end
    hideAll()
end

--- Check measure statuses and read their string values.
--- Returns array of { read value, measureInfo reference } with 0th value as array size.
local function getNames()
    --- int -> { measureStringValue, measureInfo }
    local tNames = {}
    local tMeasures = tMeasures
    local nIndex = 0
    for i = 1, nMeasureCount, 1 do
        local nValue = tMeasures[i][1]:GetValue()
        if (nValue ~= 0) then
            nIndex = nIndex + 1
            local tValue = {}
            tNames[nIndex] = tValue
            tValue[1] = tMeasures[i][1]:GetStringValue()
            tValue[2] = tMeasures[i]
        else
            -- correct if use with perfmonPDH and natural indexing
            break
        end
    end
    tNames[0] = nIndex
    return tNames
end

local function nextMapIndex()
    local result = nMapIndex
    nMapIndex = nMapIndex + 1
    return result
end

--- Ensures that all names have some assigned meter groups
local function updateNameMapping(tNames)
    local tMap = tMap
    local nNamesCount = tNames[0]
    for i = 1, nNamesCount, 1 do
        local tMapValue = tMap[tNames[i][1]]
        if (tMapValue == nil) then
            tMap[tNames[i][1]] = nextMapIndex()
        end
    end
end

--- Sets new meter positions and disables hiding
local function updateMetersMapping(tNames)
    local tMap = tMap
    local tMeters = tMeters
    local tPlaceMap = tPlaceMap

    local nNamesCount = tNames[0]
    for i = 1, nNamesCount, 1 do
        local meterInfo = tMeters[tMap[tNames[i][1]]]
        local baseHandle = meterInfo[1]
        baseHandle:SetX(tPlaceMap[i * 2 - 1])
        baseHandle:SetY(tPlaceMap[i * 2])
        SKIN:Bang("!SetOptionGroup", meterInfo[2], 'Hidden', '0')
        for j = 1, nValuesCount, 1 do
            SKIN:Bang('!SetOption', meterInfo[j * 2 + 1], meterInfo[j * 2 + 2], tNames[i][2][j + 1])
        end
    end
end

function Update()
    if (bError) then
        return 'error'
    end
    if (bNeedUpdate == false) then
        return 'ok'
    end
    bNeedUpdate = false

    local tNames = getNames()
    updateNameMapping(tNames)
    hideAll()
    updateMetersMapping(tNames)

    return 'changed'
end
